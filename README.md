# PS3 Update List Parser

PS3 Update List Parser is a parse snippet written in Java that will fetch information about the latest update, such as the version number, patch CDN link, and update.pup CDN link. The .txt file is parsed into a hashmap and the following keys will be found:

"Dest" 								=> The country code of the destination, 84 is the USA

"CompatibleSystemSoftwareVersion" 	=> The latest update, as of writing 4.80

"IncrementalUpdateVersion" 			=> The incremental update version code

"SystemSoftwareVersion" 			=> The latest update, as of writing 4.80

"CDNPATCH" 							=> The link for the PS3PATCH.PUP file

"CDN" 								=> The link for the PS3UPDAT.PUP file

"CDN_Timeout" 						=> The timeout (in seconds) for the CDN

# Documentation

All documentation for this project can be found in /dist/javadoc.

# License

This project is distributed under the Apache 2.0 License. For more information, see the "LICENSE.md" file in the root directory.