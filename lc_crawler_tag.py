# -*- coding: utf-8 -*- 
import urllib2
import re
import os
from xml.sax.saxutils import unescape
from bs4 import BeautifulSoup
import sys
import shutil
reload(sys)  
sys.setdefaultencoding('utf8')

def get_html(url):
	# pass url, return html code in plain text
	try:
		headers = {'User-Agent' : 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:43.0) Gecko/20100101 Firefox/43.0'}
		request = urllib2.Request(url, headers = headers)
		page = urllib2.urlopen(request)
		html = page.read()
		page.close()
		return html
	except urllib2.URLError as e:
		raise e

def get_problem_from_html(html):
	problems = re.findall('<span class="m-l-sm title">\n *(.*?) (.*?) *\n</span>.*?<span class="label difficulty.*?>\n *(.*?)\n</span>', html, re.S)
	return problems

def get_problem_by_tag(tag_url):
	# get tags
	tag_html = get_html(tag_url)
	tags = re.findall('<span class="m-l-sm">\n *(.*?)\n *</span>', tag_html, re.S)
	# get problems from each tag
	problems = {}
	rep = re.compile(' ')
	tag_file = open('tags.txt', 'w')
	for tag in tags:
		url = tag_url + rep.sub("-", tag).lower()
		try:
			html = get_html(url)
			tag_problem = get_problem_from_html(html)
			problems[tag] = tag_problem
			print "Got tag '%s'" % tag
		except urllib2.URLError as e:
			print 'Failed to get tag %s from %s' % (tag, url)
		# save tag as file
		tag_file.write(tag + '\n')
	tag_file.close()
	return problems

def save_file(each_tag, each_problem, flag, data):
	file_name = path + each_tag + '/' + each_problem[1] + flag + '.txt'
	ans_file = open(file_name, 'w');
	ans_file.write(data)
	ans_file.close()

def isEmpty_bf(raw):
	if len(raw) == 0:
		return ''
	else:
		raw_soup = BeautifulSoup(raw[0], "lxml")
		return ''.join(raw_soup.find_all(text=True))

def get_prob_description(each_problem, que_html):
	# difficulty descripton
	diff_raw = re.findall('data-original-title=(.*?)style', que_html, re.S)
	diff = isEmpty_bf(diff_raw)
	# question descripton
	ques_raw = re.findall('<div class="panel-body">.*?<p>(.*?)</p>', que_html, re.S)
	ques = isEmpty_bf(ques_raw)
	# other description
	description_raw = re.findall('<div class="m-t-lg m-b-lg">(.*?)<b>Timer</b>', que_html, re.S)
	description = isEmpty_bf(description_raw)
	description = description.replace('\n\n', '').replace('Expand 2', '').replace('Expand ', '').replace('  ', '').replace('Related Problems', '\nRelated Problems').replace('Tags', '\nTags').replace('\n show company tags ', '')
	# put them together
	all_description = (each_problem[0] + each_problem[1] + '\n\n' + diff + '\n' + ques + '\n\n' + description).strip()
	all_description = ('%s: %s\n\n%s\n%s\n\n%s' % (each_problem[0], each_problem[1], diff, ques, description)).strip()
	# all_description = (diff + '\n' + ques + '\n' + description).strip()
	return all_description

def get_code(ans_html):
	ans_raw = re.findall('<pre class="prettyprint nicefont">(.*?)</pre>', ans_html, re.S)
	# add description
	# use unescape to replace the xml character reference
	code = unescape(ans_raw[0], {"&apos;": "'", "&quot;": '"'}).strip()
	return code

# get problem description and answer code
def get_answer(problems, ans_url_base, que_url_base):
	rep = re.compile(' ')
	log_file = open('log.txt', 'w')
	tot_cnt = 0
	success_cnt = 0
	fail_cnt = 0
	for each_tag in problems:
	# for each_tag in ['Union Find', 'Yahoo']:
		print 'Tag: %s' % each_tag
		os.mkdir(path + each_tag)
		for each_problem in problems[each_tag]:
			# get each problem in current tag
			encoded_problem = rep.sub('-', each_problem[1])
			ans_url = (ans_url_base + encoded_problem).lower()
			que_url = (que_url_base + encoded_problem).lower()
			# try:
			try:
				# question page ----------
				all_description = ''
				try:
					que_html = get_html(que_url)
					all_description = get_prob_description(each_problem, que_html)
					save_file(each_tag, each_problem, '_description', all_description)
				except urllib2.URLError as e:
					raise e
				# answer page ----------
				try:
					ans_html = get_html(ans_url)
					code = get_code(ans_html)
					# Save code as file
					save_file(each_tag, each_problem, '_code', code[372:])
				except urllib2.URLError as e:
					# print "Can't open answer page of %s, error message: %s" % (each_problem[1], e)
					raise e
				success_cnt = success_cnt + 1
				tot_cnt = tot_cnt + 1
				print '\t%s\t[OK]\t\t%s' % (tot_cnt, each_problem[1])
			except urllib2.URLError as e:
				fail_cnt = fail_cnt + 1
				tot_cnt = tot_cnt + 1
				log_file.write(str(fail_cnt) + ':\t' + each_problem[1] + ' Failed. Message: %s\n' % e)
				print '\t%s\t[Failed]\t%s' % (tot_cnt, each_problem[1])
	print '\n>>> ', tot_cnt, ' Finished, ', success_cnt, ' succeeded, ', fail_cnt, ' failed.'
	log_file.close()

if __name__ == '__main__':
	global path
	path = 'Answer/'
	if (os.path.exists(path)):
		shutil.rmtree(path)
		os.mkdir(path)
	else:
		os.mkdir(path)

	tag_url = 'http://www.lintcode.com/en/tag/'
	problems = get_problem_by_tag(tag_url)
	#problems = {'Union Find':[['1', 'Graph Valid Tree', 'Medium'], ['2', 'Surrounded Regions', 'Medium']],
	#			'Yahoo':[['3', 'Binary Tree Serialization', 'Medium']]}
	answer_url = 'http://www.jiuzhang.com/solutions/'
	question_url = 'http://www.lintcode.com/en/problem/'
	get_answer(problems, answer_url, question_url)
	print 'Done'
