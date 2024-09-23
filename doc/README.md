# Doc


## Sample sql

http://localhost:8080/h2-console/login.do

```sql
select * from customer;
select * from shiori;
select * from page;
select * from block;
```


## Sample curl

```sh
# Customer
curl -X POST localhost:8080/v1/customer \
-H 'Content-Type: application/json' \
-d '{"name": "Tiori Yamada"}' | jq .
# {"header":{"status":"S","code":"0000","message":"Successful"},"body":{"name":"Tiori Yamada","uid":"bd5594bf-8d4f-4a11-9206-096faddcf1b3"}}
UUID=37b79f1a-c9ce-4b65-941b-96d412fd998c

# Auth
curl -X GET localhost:8080/v1/auth -H "X-Tiori-User:${UUID}" | jq .

# Shiori
curl -X GET localhost:8080/v1/shiori/list \
-H "X-Tiori-User:${UUID}" | jq .

curl -X GET localhost:8080/v1/shiori/1 \
-H "X-Tiori-User:${UUID}" | jq .

curl -X POST localhost:8080/v1/shiori \
-H "X-Tiori-User:${UUID}" \
-H 'Content-Type: application/json' \
-d '{"title": "Shiori title"}'
curl -X POST localhost:8080/v1/shiori \
-H "X-Tiori-User:${UUID}" \
-H 'Content-Type: application/json' \
-d '{"title": "Shiori title 2"}'

curl -X PUT localhost:8080/v1/shiori/1 \
-H "X-Tiori-User:${UUID}" \
-H 'Content-Type: application/json' \
-d '{"title": "Shiori title NEW"}'

curl -X DELETE localhost:8080/v1/shiori/1 \
-H "X-Tiori-User:${UUID}"


# Page
curl -X GET localhost:8080/v1/page/1 \
-H "X-Tiori-User:${UUID}"
curl -X GET localhost:8080/v1/page/2 \
-H "X-Tiori-User:${UUID}"

curl -X POST localhost:8080/v1/page \
-H "X-Tiori-User:${UUID}" \
-H 'Content-Type: application/json' \
-d '{"shioriId": 1, "layout": "{ list:[{\"type\":\"SIMPLE_100\", \"blockIdList\":[1] }]}"}'
curl -X POST localhost:8080/v1/page \
-H "X-Tiori-User:${UUID}" \
-H 'Content-Type: application/json' \
-d '{"shioriId": 1, "layout": "{ list:[{\"type\":\"SIMPLE_100\", \"blockIdList\":[1] }]}"}'

curl -X PUT localhost:8080/v1/page/2 \
-H "X-Tiori-User:${UUID}" \
-H 'Content-Type: application/json' \
-d '{"shioriId": 1, "layout": "{ list:[{\"type\":\"SIMPLE_100\", \"blockIdList\":[1] }, {\"type\":\"SIMPLE_50_50\", \"blockIdList\":[2,3] }]}"}'

curl -X DELETE localhost:8080/v1/page/1 \
-H "X-Tiori-User:${UUID}"


# Block
curl -X POST localhost:8080/v1/block \
-H "X-Tiori-User:${UUID}" \
-H 'Content-Type: application/json' \
-d '{"pageId": 1, "type": "TITLE", "content": "{ \"text\":\"t1\", \"description\":\"d1\" }"}'
curl -X POST localhost:8080/v1/block \
-H "X-Tiori-User:${UUID}" \
-H 'Content-Type: application/json' \
-d '{"pageId": 2, "type": "TITLE", "content": "{ \"text\":\"t2\", \"description\":\"d2\" }"}'
curl -X POST localhost:8080/v1/block \
-H "X-Tiori-User:${UUID}" \
-H 'Content-Type: application/json' \
-d '{"pageId": 2, "type": "TEXT", "content": "{ \"text\":\"t3\" }"}'

curl -X PUT localhost:8080/v1/block/1 \
-H "X-Tiori-User:${UUID}" \
-H 'Content-Type: application/json' \
-d '{"pageId": 1, "type": "TITLE", "content": "{ \"text\":\"t1 NEW\", \"description\":\"d1 NEW\" }"}'

curl -X DELETE localhost:8080/v1/block/1 \
-H "X-Tiori-User:${UUID}"


# Load all
curl -X POST localhost:8080/v1/shiori/load \
-H "X-Tiori-User:${UUID}" \
-H 'Content-Type: application/json' \
-d '{"shiori":{"id":20,"title":"Shiori title 20","pageList":[{"id":20,"layout":"{ list:[{\"type\":\"SIMPLE_100\", \"blockIdList\":[20] }]}","blockList":[{"id":20,"type":"TITLE","content":"{ \"text\":\"t20\", \"description\":\"d20\" }"}]}]}}}'
curl -X POST localhost:8080/v1/shiori/load \
-H "X-Tiori-User:${UUID}" \
-H 'Content-Type: application/json' \
-d '{"shiori":{"id":10,"title":"Shiori title 10","pageList":[{"id":10,"layout":"{ list:[{\"type\":\"SIMPLE_100\", \"blockIdList\":[10] }]}","blockList":[{"id":10,"type":"TITLE","content":"{ \"text\":\"t10\", \"description\":\"d10\" }"},{"id":11,"type":"TITLE","content":"{ \"text\":\"t11\", \"description\":\"d11\" }"}]}]}}}'

curl -X GET localhost:8080/v1/shiori/10 \
-H "X-Tiori-User:${UUID}" | jq .

```
