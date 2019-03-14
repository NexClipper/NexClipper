function textCut (str) {
	if (str.length < 30)
		return str
	else 
		return str.substr(0, 30) + '...'
}