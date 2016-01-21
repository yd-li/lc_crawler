import urllib2
import re

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

def get_tags(html):
	tags = re.findall('<span class="m-l-sm">\n *(.*?)\n *</span>', html, re.S)
	return tags

def get_problem_from_html(html):
	problems = re.findall('<span class="m-l-sm title">\n *(.*?) (.*?) *\n</span>.*?<span class="label difficulty.*?>\n *(.*?)\n</span>', html, re.S)
	return problems

def get_problem_by_tag(tags):
	base_url = 'http://www.lintcode.com/en/tag/'
	rep = re.compile(' ')
	for tag in tags:
		url = base_url + rep.sub("-", tag).lower()
		print url
		html = get_html(url)
		problem = get_problem_from_html(html)
		output = open('saved/' + tag + '.txt', 'w')
		output.write(str(problem))
		output.close()

def main():
	url = 'http://www.lintcode.com/en/tag/'
	html = get_html(url)
	tags = get_tags(html)
	output = open('output.txt', 'w')
	for item in tags:
		output.write(item+'\n')
	output.close()
	get_problem_by_tag(tags)
	print 'done'

if __name__ == '__main__':
    main()