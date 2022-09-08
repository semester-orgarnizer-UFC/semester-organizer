FROM node:16
WORKDIR /home/app/frontend
COPY frontend /home/app/frontend
RUN npm install
RUN npm run build
CMD [ "npm", "start" ]