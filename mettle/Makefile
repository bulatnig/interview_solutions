.PHONY: init push-test delete-test

init:
	git remote add private git@github.com:eeveebank/remote-pair-programming-test-private

push-test:
	git checkout -b car-api && git pull private car-api && git push origin car-api

delete-test:
	git reset --hard && git checkout master && git branch -D car-api && git push -d origin car-api
