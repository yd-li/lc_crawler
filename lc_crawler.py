import urllib2
import re
import os
from xml.sax.saxutils import unescape

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
	# TODO: let url fully load
	html = get_html(problem_url)
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

def get_answer(problems, ans_url_base):
	base = ans_url_base
	rep = re.compile(' ')
	log_file = open('log.txt', 'w')
	tot_cnt = 0
	success_cnt = 0
	fail_cnt = 0
	for eachItem in problems:
		# get each problem
		eachItem = unescape(eachItem.strip(), {'&apos;': "'", '&quot;': '"'})
		splited = eachItem.split(' ', 1)
		eachProblem = splited[1]
		encoded_problem = rep.sub("-", eachProblem)
		ans_url = (base + encoded_problem).lower()
		# use HTTP request to get and save answer code
		try:
			html = get_html(ans_url)
			item = re.findall('<pre class="prettyprint nicefont">(.*?)</pre>', html, re.S)
			code = unescape(item[0], {"&apos;": "'", "&quot;": '"'})	# replace the xml character reference
			# Save code as file
			save_answer_code(eachProblem, code)
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
	problems = get_problems(problem_url)
	get_answer(problems, answer_url)

if __name__ == '__main__':
    main()