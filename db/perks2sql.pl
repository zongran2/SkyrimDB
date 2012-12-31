#!/usr/bin/perl -w

foreach $line (<STDIN>){
    if ($line =~ /<tr>\s*<td.*?>\s*([0-9,a-f,A-F]{8})\s*<\/td><td.*?>\s*(\S*)\s*<\/td>.*/){
        print "INSERT INTO perks(form_id,editor_id) VALUES('$1', '$2');\n";
    } else {
        print STDERR "Invalid line: $line";
    }
}

