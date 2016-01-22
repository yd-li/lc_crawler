import urllib2
import re
import os
from xml.sax.saxutils import unescape
from bs4 import BeautifulSoup
import sys  
reload(sys)  
sys.setdefaultencoding('utf8')

def get_html(url):	
	# pass url, return html code in plain text
	try:
		request = urllib2.Request(url, headers={'User-Agent' : 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:43.0) Gecko/20100101 Firefox/43.0'})
		page = urllib2.urlopen(request)
		html = page.read()
		page.close()
		return html
	except urllib2.URLError as e:
		raise e

def save_array_txt(array, txt_name):
	txt_file = open(txt_name + '.txt', 'w')
	for e in array:
		txt_file.write("%s\n" % e.strip())
	txt_file.close()
	print 'ok...' + txt_name + '.txt'

def get_problems(problem_url):
	# return all problems as a list
	# TODO: let url fully load --> failed!

	# with open('html.txt', 'r') as myfile:
	#    html=myfile.read()

	# html = get_html(problem_url)
	txt_file = open('html.txt', 'r')
	html = txt_file.read()
	# add description
	problems = re.findall('<span class="m-l-sm title">\n(.*?)\n</span>', html, re.S)
	save_array_txt(problems, 'problems')
	print 'ok...get_problems'
	return problems

def save_answer_code(file_name, code):
	if (os.path.exists('Answer')):
		pass
	else:
		os.mkdir('Answer')
	answer_file = open('Answer/' + file_name + '.java', 'w')
	answer_file.write(code)
	answer_file.close()

def get_answer(problems, ans_url_base, que_url_base):
	rep = re.compile(' ')
	log_file = open('log.txt', 'w')
	tot_cnt = 0
	success_cnt = 0
	fail_cnt = 0
	for eachItem in problems:
		# get each problem
		eachItem = unescape(eachItem.strip(), {'&apos;': "'", '&quot;': '"'})
		splited = eachItem.split(' ', 1)
		eachNum = splited[0]
		eachProblem = splited[1]
		encoded_problem = rep.sub("-", eachProblem)
		ans_url = (ans_url_base + encoded_problem).lower()
		que_url = (que_url_base + encoded_problem).lower()
		# use HTTP request to get and save answer code
		try:
			# question page ----------
			que_html = get_html(que_url)
			# question descripton
			ques_raw = re.findall('<div class="panel-body">.*?<p>(.*?)</p>', que_html, re.S)
			if len(ques_raw) == 0:
				ques = ''
			else:
				que_soup = BeautifulSoup(ques_raw[0], "lxml")
				ques = ''.join(que_soup.find_all(text=True))
			# example descripton
			example_raw = re.findall('<div class="m-t-lg m-b-lg">(.*?)</div>', que_html, re.S)
			if len(example_raw) == 0:
				example = ''
			else:
				example_soup = BeautifulSoup(example_raw[0], "lxml")
				example = ''.join(example_soup.find_all(text=True))
			# put them together
			number_ques_example = 'problem_start' + '\n' + eachItem + '\n' + '\n' + ques + '\n' + '\n' + example + '\n' + '\n'
			# answer page ----------
			ans_html = get_html(ans_url)
			ans_raw = re.findall('<pre class="prettyprint nicefont">(.*?)</pre>', ans_html, re.S)
			# add description
			code = unescape(ans_raw[0], {"&apos;": "'", "&quot;": '"'})	# replace the xml character reference
			code = code + '\n' + 'problem_end'
			# Save code as file
			save_answer_code(eachItem, number_ques_example + code[372:])
			success_cnt = success_cnt + 1
			tot_cnt = tot_cnt + 1
			print tot_cnt, '\t [ OK ]\t\t', eachProblem
		except urllib2.URLError as e:
			fail_cnt = fail_cnt + 1
			tot_cnt = tot_cnt + 1
			log_file.write(str(fail_cnt) + ':\t' + eachProblem + ' Failed.\n\tURL: ' + ans_url + '\n')
			print tot_cnt, '\t [ Failed ]\t', eachProblem

	print '>>> ', tot_cnt, ' Finished, ', success_cnt, ' succeeded, ', fail_cnt, ' failed.'
	log_file.close()

def main():
	problem_url = 'http://www.lintcode.com/en/problem/'
	answer_url = 'http://www.jiuzhang.com/solutions/'
	question_url = 'http://www.lintcode.com/en/problem/'
	problems = get_problems(problem_url)
	get_answer(problems, answer_url, question_url)

if __name__ == '__main__':
    main()
