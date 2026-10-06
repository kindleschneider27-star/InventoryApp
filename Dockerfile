FROM ubuntu:latest
LABEL authors="kmile"

ENTRYPOINT ["top", "-b"]