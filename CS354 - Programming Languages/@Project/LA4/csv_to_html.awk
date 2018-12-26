
 
 BEGIN {
	FS = ","
	print " <html>"
	print "  "
	print " <head>"
	print "	   <title> Awk Language Assignment </title>"
	print " </head>" 
	print "  "
	print " <body>"
	print "	   <h1> single-family dwelling </h1>"	
	print " "
	print " <table>"
	print " "
 }
 {
	$3 = tolower($3)
 }
 $3 ~ /single family dwelling/ {

	if((substr($3, 1, 22)) == "single family dwelling")
	{
		print " <tr>"
		print "	  <td> " $1 " </td>"
		print "	  <td> " $3 " </td>"
		print "	  <td> " $5 " </td>"
		print "	  <td> " $6 " </td>"
		print "	  <td> " $7 " </td>"
		print "	  <td> " $8 " </td>"
		print " </tr>"
 		print " "		
	}			
 }

 
 END  {
	print " </table>"
	print " "
	print " </body>"
	print " "
	print " </html>"
 }

