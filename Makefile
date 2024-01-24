FILE_PATH?=tests/input.txt
DOCKER_CMD=docker run --rm -it \
	--mount type=bind,source=${PWD},target=/home/groovy/scripts \
	-w /home/groovy/scripts \
	groovy:alpine


test:
	@cmd_prefix=''; \
	if ! command -v groovy &>/dev/null; then \
		cmd_prefix="${DOCKER_CMD}"; \
	fi; \
	$${cmd_prefix} groovy tests/unit_test.groovy

run:
	@cmd_prefix=''; \
	if ! command -v groovy &>/dev/null; then \
		cmd_prefix="${DOCKER_CMD}"; \
	fi; \
	$${cmd_prefix} groovy word_count.groovy ${FILE_PATH}

build:
	rm adaptavist_engineer_assessment_rjszynal_*
	tar zcvf adaptavist_engineer_assessment_rjszynal_$(shell date +%y%m%d).tar.gz *

.PHONY: test run build
