build:
	docker build -t kichutov_openai .

run:
	docker run -p 8080:8080 --name kichutov_openai --rm kichutov_openai