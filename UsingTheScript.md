# Introduction #
sTodo now using JavaScript to support the ability for end-user to customize it. And almost all component used in sTodo is publiced in JavaScript, of course, some API, such as: new an item, send the item to a friend via e-mail, etc.

In this page, I'll introduce you how to using those JavaScript functions to do what you want to and of course, how to extend the API sTodo publiced.


---

# Details #
Here are some examples:

## Using Swing to make a Alert dialog ##
```

//show the message as a message dialog
function alert(message){
	JOptionPane.showMessageDialog(
			null, 
			message, 
			"Alert", 
			JOptionPane.INFORMATION_MESSAGE);
}

```

![http://stodo.googlecode.com/files/Alert.png](http://stodo.googlecode.com/files/Alert.png)

## Log information on the console ##
```

//just print a message on the console
function log(message){
	var prefix = "[stodo log trace] ";
	if(!println){
		println = function(message){
			print(prefix+message+"\n");
		}
	}
	println(prefix+message);
}

```

## Insert an new item via JavaScript ##

```

function insertNewTodo(def){
	//the default new to-do template
	var item = createNewTodo(def);
	DataModel.addItem(JSON.stringify(item));
}

//insertNewTodo({desc : "Gonna to kill bill", timeout : "tomorrow 10 am" });
//insertNewTodo({desc : "Relive bill killed", timeout : "5/1 9 pm" });

```

![http://stodo.googlecode.com/files/newItem.png](http://stodo.googlecode.com/files/newItem.png)
## Send a e-mail to friend ##
```

sendEmail({
	to : "juntao.qiu@gmail.com",
	subject : "script test",
	content : createNewTodo(null)
});

```


---