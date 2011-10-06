<?php
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
	$title = 'Home';
	$includefile = './content/home.htm';
}

header("Content-Type: text/html; charset=UTF-8");
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
	<a href="http://validator.w3.org/w3c-validator/check?uri=referer"><img
        src="http://www.w3.org/Icons/valid-xhtml11"
        alt="Valid XHTML 1.1" height="31" width="88" /></a>
</p>
</body>
<?php
if (!preg_match('/^(127|172)/',$_SERVER['SERVER_ADDR']))
{
?>
<!-- Piwik -->
<script type="text/javascript">
var pkBaseURL = (("https:" == document.location.protocol) ? "https://stats.rockstats.de/" : "http://stats.rockstats.de/");
document.write(unescape("%3Cscript src='" + pkBaseURL + "piwik.js' type='text/javascript'%3E%3C/script%3E"));
</script><script type="text/javascript">
try {
var piwikTracker = Piwik.getTracker(pkBaseURL + "piwik.php", 13);
piwikTracker.trackPageView();
piwikTracker.enableLinkTracking();
} catch( err ) {}
</script><noscript><p><img src="http://stats.rockstats.de/piwik.php?idsite=13" style="border:0" alt="" /></p></noscript>
<!-- End Piwik Tracking Code -->
<?php
}
?>
</html>
