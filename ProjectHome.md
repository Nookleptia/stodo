## What is _**sTodo**_ ##

_**sTodo**_ is moved to github recently: sTodo https://github.com/abruzzi/stodo

_**sTodo**_ is a todo-list manager, you can add, remove todos in it, each task(todo) has its own status, such as new, pending, canceled, finished, etc.

I am trying to make it to be a more useful & powerful todo manager, by now, you can **add**, **remove**, **edit**, **search** you _**todos**_, and can **send you todo** to others by email, and I am still working on the exporting part, which will support export the todo list to **HTML**, **Plain Text** and **Excel**, etc.

While the exporting part of _**sTodo**_ is still in the mill, so the alpha version may be delay for 2 weeks or more(current version is v0.2).

Also, I don't want to develop all the functions it should have, so, I'm thinking to provide a _**pluggable**_ framework, and then everyone else can customize the _**sTodo**_ by himself. Actually, I made another simple todo manager with _**pluggable**_ framework which could work by now, but not that easy to use. The script used on _**sTodo**_ will be JavaScript, which is used on almost every Web-Page, and it can be used on java since JDK 1.6.

If you have any good ideas, please share them to me.


---

**_sTodo_** has been ported to Mac and been prove %100 Free and Clear by [SoftPedia](http://www.softpedia.com/), so you can use it freely.
[![](http://www.softpedia.com/images/softpedia_download_small.gif)](http://mac.softpedia.com/get/Business/sTodo.shtml)

**Requirements**:

_Java 6 or later_

---

## New Features ##
**Notify** when timeout
|![http://stodo.googlecode.com/files/Alerm.png](http://stodo.googlecode.com/files/Alerm.png)|
|:------------------------------------------------------------------------------------------|

**Englise** like time definition
|![http://stodo.googlecode.com/files/newItem.png](http://stodo.googlecode.com/files/newItem.png)|
|:----------------------------------------------------------------------------------------------|

**Search** an existed item
|![http://stodo.googlecode.com/files/search.png](http://stodo.googlecode.com/files/search.png)|
|:--------------------------------------------------------------------------------------------|


---

## What it looks like ##

|![http://stodo.googlecode.com/files/sTodo0.5.png](http://stodo.googlecode.com/files/sTodo0.5.png)|![http://stodo.googlecode.com/files/export.png](http://stodo.googlecode.com/files/export.png)|
|:------------------------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------|
|![http://stodo.googlecode.com/files/stodo_edit.png](http://stodo.googlecode.com/files/stodo_edit.png)|![http://stodo.googlecode.com/files/send_mail.png](http://stodo.googlecode.com/files/send_mail.png)|


---


## Change Log ##
_Vsersion 0.5_
Public some more UI components and API to scripting, and fixed some bugs. Support user to do some more by using the scripts. More details please check the wiki pages.

_Version 0.45_
Add notification mechanism to _sTodo_, may add some **_usable_** ways of edit date and time, after add the notification, _sTodo_ becomes useful.

_Version 0.4_
Add plugin-framework, and then user who using _sTodo_ can define their own functions in scripts. By now, this framework is not that nice, I need more time to really finish it.

In v0.4, I defined a simple plugin which can list all plugins actived in the application.

_Version 0.3_
In-time search function added. And changed the icon on system tray from scheduler to a clock.
In addition, a few keyboard shortcuts added, such as slash '/' to open the search panel, and `ESC` to cancel(hide) the panel.


_Version 0.2_
Add mail support, which allow the user mail one item to a special mail address, this may be useful if you have one computer for work, and you have an idea when you ready to come back to home, then you just submit a message to your mail, when you get home, check your mail to get notes (actually, that's exactly what I do almost everyday).


_Version 0.1_
A very simple application which can only add new task into a list, and then show it.