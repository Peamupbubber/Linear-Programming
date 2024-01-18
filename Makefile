make: Main.java
	javac Main.java
run: Main.java
	rm *.class
	javac Main.java
	java Main LP.txt
clean:
	rm *.class