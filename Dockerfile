FROM ubuntu:latest
LABEL authors="kader"

ENTRYPOINT ["top", "-b"]