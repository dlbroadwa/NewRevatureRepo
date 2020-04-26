class TodoItem {
  // JS classes all have a special constructor method named "constructor"
  // it is used like constructors in other languages
  // JS classes can extend other classes
  //if your JS class extends another class your must call super() in your constructor
  constructor(id, name) {
    this._id = id;
    this._name = name;
  }

  // there are no private modifiers in JS. An _ is generally used to denote a property that should be -->
  // treated as a private member
  // use get and set keywords to create getter and setter properties to manipulate "private" memebers

  get id() {
    return this._id;
  }

  set id(id) {
    this._id = id;
  }

  get name() {
    return this._name;
  }

  set name(name) {
    this._name = name;
  }
}

class TodoList {
  constructor(id, name, todos) {
    this._name = name;
    this._id = id;
    this._todos = todos || [];
  }

  get name() {
    return this._name;
  }

  set name(name) {
    this._name = name;
  }

  get todos() {
    return this._todos;
  }

  set todos(todos) {
    this._todos = todos;
  }

  get id() {
    return this._id;
  }

  set id(id) {
    this._id = id;
  }
}

const todoLists = [
  new TodoList(1, 'Karate Kid', [new TodoItem(1, 'Paint the fence'), new TodoItem(2,'Sweep the leg'), new TodoItem(3,'Finish him!')]),
  new TodoList(2, "Bob The Builder", [new TodoItem(1, 'Build stuff'), new TodoItem(2, 'Fix stuff'), new TodoItem(3, 'Put on hardhat')])
];


let currentList = null;
let listCounter = 2;

// loop over all the todo lists adding each to an aside list for later displaying
todoLists.forEach((e) => {
  const li = document.createElement('li'); // create a disconnected element
  li.innerHTML = e.name;
  li.setAttribute('data-id', e.id);

  // add a click listener to the li for more interactivity
  // this will set the current list to displayed on the other section of the page
  li.addEventListener('click', (e) => {
    const targetId = parseInt(e.target.getAttribute('data-id'));
    currentList = todoLists.find((l) => l.id === targetId); // high order function
    renderList();
  });
  document.querySelector('#lists-list').appendChild(li); // connect the element to the tree as a child of ul#lists-list
});
function renderList() {
  document.querySelector('#current-list-target').innerHTML = '';
  currentList.todos.forEach((t) => {
    const span = document.createElement('li');
    span.innerHTML = t.name;
    document.querySelector('#current-list-target').appendChild(span);
  });
}

if (currentList === null) {
  document.querySelector('#current-list-target').innerHTML =
    'No list to display';
}
function clicker(element){
  document.getElementById('list_creator').reset();
  document.getElementById('list_creator').style.display="block";      
}
function listMaker(name, number){
  // alert("You've entered " + name.value + " and " + number.value);

  document.getElementById('list_creator').style.display="none";
  listCounter++;
  var arr = [];
  var i =1;
  while(i<=number.value){
    var listItem = prompt("Enter list item #"+i,"");
    var thingy = new TodoItem(i, listItem);
    arr.push(thingy);
    i++
  }
  var newList = new TodoList(listCounter, name.value, arr);
  todoLists.push(newList);
 
  const li = document.createElement('li'); // create a disconnected element
  li.innerHTML = newList.name;
  li.setAttribute('data-id', newList.id);
  li.addEventListener('click', (e) => {
    const targetId = parseInt(e.target.getAttribute('data-id'));
    currentList = todoLists.find((l) => l.id === targetId); // high order function
    renderList();
  });
  document.querySelector('#lists-list').appendChild(li); // connect the element to the tree as a child of ul#lists-list
}
