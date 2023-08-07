FROM python
WORKDIR /home/app/scrapping
COPY scrapping /home/app/scrapping
RUN pip install --no-cache-dir --upgrade pip 
RUN pip install fastapi
RUN pip install selenium
RUN pip install uvicorn
RUN echo "deb http://deb.debian.org/debian/ unstable main contrib non-free" >> /etc/apt/sources.list.d/debian.list
RUN apt-get update
RUN apt-get install -y --no-install-recommends firefox
CMD ["python3", "main.py"]