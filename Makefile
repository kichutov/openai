build:
	docker build -t kichutov_openai .

run:
	docker run -d -p 8080:8080 --name kichutov_openai --rm kichutov_openai