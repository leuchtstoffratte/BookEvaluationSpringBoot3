#BookEdition:
#{
#	"book":{
#			"title":"effective java",
#			"author":{
#				"name":"martin",
#				"surname":"fowler",
#				"authorId":1
#				}
#			},
#	"isbn":"lalala",
#	"publisher":{
#				"publisherName":"random-house"
#			},
#	"publishedDate":2024-03-17,
#	"edition":1,
#	"form":"HARDCOVER"
#}


#curl -iv localhost:8080/rest/library/addBook -H "Content-Type:application/json" -d "{\"book\":{\"title\":\"effective java\",\"author\":{\"name\":\"martin\",\"surname\":\"fowler\",\"authorId\":1}},\"isbn\":\"lalala\",\"publisher\":{\"publisherName\":\"random-house\"},\"publishedDate\":\"2024-03-17\",\"edition\":1,\"form\":\"HARDCOVER\"}"



curl -iv localhost:8081/rest/library/addBook -H "Content-Type:application/json" -d "{\"book\":{\"title\":\"effective java\",\"author\":{\"name\":\"Joshua\",\"surname\":\"Bloch\",\"authorId\":1}},\"isbn\":\"lalala\",\"publishedDate\":\"2024-03-17\",\"edition\":1,\"form\":\"HARDCOVER\"}"

curl -iv -X GET localhost:8081/rest/library/listBooks -H "Content-Type:application/json" -d "{\"name\":\"Joshua\",\"surname\":\"Bloch\",\"authorId\":1}"