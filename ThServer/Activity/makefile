n_test = localhost:50000
n2_nodes = localhost:50000 localhost:50001
n3_nodes = localhost:50000 localhost:50001 localhost:50002 
node = $(node)

compile:
	javac program.java

run_test:
	java program $(node) $(n_test)

run_2_nodes:
	java program $(node) $(n2_nodes)

run_3_nodes:
	java program $(node) $(n3_nodes)
