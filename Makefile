make: Main.java
	javac Main.java
run: Main.class
	java Main LP.txt
clean: *.class
	rm *.class