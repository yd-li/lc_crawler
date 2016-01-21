import urllib2
import re

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

def get_tags(html):
	tags = re.findall('<span class="m-l-sm">\n *(.*?)\n *</span>', html, re.S)
	return tags

def main():
	url = 'http://www.lintcode.com/en/tag/'
	html = get_html(url)
	tags = get_tags(html)
	output = open('output.txt', 'w')
	for item in tags:
		output.write(item+'\n')
	output.close()

if __name__ == '__main__':
    main()