<?php
/*
	Webseiten für wedabecha.org
	(C) Copyright 2007 by Dominic Hopf <dh@dmaphy.de>
*/

if (isset($_GET['view']))
{
	$includefile = './content/' . $_GET['view'] . '.htm'; 
	if (file_exists($includefile))
	{
		$title = ucfirst($_GET['view']);
	}
	else
	{
		$includefile = './content/home.htm';
	}
}
else
{
	$includefile = './content/home.htm';
}

print '<?xml version="1.0" encoding="UTF-8"?>';
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>wedabecha - <?php print $title; ?></title>
	<link rel="stylesheet" type="text/css" href="./mainstyle.css" />
</head>
<body>
<?php
include('./content/navigation.htm');
include($includefile);
?>
<p style="text-align: center; padding-top: 50px;">
<a href="http://developer.berlios.de" title="BerliOS Developer">
<img src="http://developer.berlios.de/bslogo.php?group_id=2470"
	style="width: 124px; height: 32px;"
	alt="BerliOS Developer Logo"></a>
</p>
<p style="text-align: center; font-size: 9pt; padding-top: 50px;">
	wedabecha ist ein <a style="font-size: 9pt;" href="http://www.codeforum.de/" target="_blank">codeforum.de</a>-Projekt
</p>
</body>
</html>
