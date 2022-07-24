FROM node:16
COPY frontend /home/app/frontend
WORKDIR /home/app/frontend
RUN npm install
RUN npm run build
CMD [ "npm", "start" ]