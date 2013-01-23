<<<<<<< HEAD
#!/usr/bin/perl -w

foreach $line (<STDIN>){
    if ($line =~ /<tr>\s*<td.*?>\s*([0-9,a-f,A-F]{8})\s*<\/td><td.*?>\s*(\S*)\s*<\/td>.*/){
        print "INSERT INTO perks(form_id,editor_id) VALUES('$1', '$2');\n";
    } else {
        print STDERR "Invalid line: $line";
    }
}

=======
#!/usr/bin/perl -w

foreach $line (<STDIN>){
    if ($line =~ /<tr>\s*<td.*?>\s*([0-9,a-f,A-F]{8})\s*<\/td><td.*?>\s*(\S*)\s*<\/td>.*/){
        print "INSERT INTO perks(form_id,editor_id) VALUES('$1', '$2');\n";
    } else {
        print STDERR "Invalid line: $line";
    }
}

>>>>>>> 59e5dadeae7ad9c475e12a5409bad8bf42f4030d
