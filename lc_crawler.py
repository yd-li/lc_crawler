import urllib2


def get_html(url):	
	request = urllib2.Request(url, headers={'User-Agent' : "Browser"})
	page = urllib2.urlopen(request)
	html = page.read()
	print 'ok...get_html'
	return html

def get_problems(html):
	problems = []
	flg = True
	index_start = 0
	index_end = 0
	while (flg):
		flg = False
		index_start = html.find('<span class="m-l-sm title">', index_end)
		if (index_start != -1):
			problem_start = html.find('\n ', index_start)
			problem_start = html.find(' ', problem_start+3)
			index_end = html.find('\n', problem_start)
			problems.append(html[problem_start + 1:index_end])
			flg = True
	print problems
	print 'ok...get_problems'


url = "http://www.lintcode.com/en/problem/"
html = get_html(url)
get_problems(html)

text_file = open("Output.txt", "w")
text_file.write(html)
text_file.close()
