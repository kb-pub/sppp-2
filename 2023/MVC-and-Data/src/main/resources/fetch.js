fetch(
	"http://localhost:8088/book/new",
	{
		method: 'POST',
		headers: {'Content-Type': 'application/json'},
		body: JSON.stringify({
			id:	7,
			title:	"book 7",
			authors: [
				{
					id: 2,
					name: "author 2"
				},
				{
					id: 4,
					name: "author 4"
				},
			]
		})
	}
)
.then(b => b.text())
.then(console.log)