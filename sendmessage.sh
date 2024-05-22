#!/bin/bash

r=$(($RANDOM % 20000))
requestBody="{\"id\": $r, \"email\":\"email@example.com\"}"
echo "sending request $requestBody"
curl -X PUT http://localhost:8080/messages -H \
 "Content-Type: application/json" -d "$requestBody"