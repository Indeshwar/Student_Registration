# pull image from docker hub
From node:alpine3.16
#Create a work directory
WORKDIR /app
#Copy the package.json to current directory
COPY package.json .
#Copy the other react code to current directory which is app directory
#install all dependencies
RUN npm install 
COPY . .
#start the app
CMD ["npm", "start"]

