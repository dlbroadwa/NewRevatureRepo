class TodoItem {
    // JS classes all have a special constructor method named "constructor"
    // it is used like constructors in other languages
    // JS classes can extend other classes
    //if your JS class extends another class your must call super() in your constructor
    constructor(id, name) {
      this._id = id;
      this._name = name;
    }

    // there are no private modifiers in JS. An _ is generally used to denote a property that should be
    // treated as a private member
    // use get and set keywords to create getter and setter properties to manipulate "private" memebers

    get id() {return this._id;}

    set id(id) {this._id = id;}

    get name() {return this._name;}

    set name(name) {this._name = name;}
  }

  class TodoList 
  {
    constructor(id, name, todos) {
      this._name = name;
      this._id = id;
      this._todos = todos || [];
    }

    get name() {return this._name;}

    set name(name) {this._name = name;}

    get todos() {return this._todos;}

    set todos(todos) {this._todos = todos;}

    get id() {return this._id;}

    set id(id) {this._id = id;}
  }

  // Create a way to input into a new list
  

  
  // const todoLists = 
  // [
  //   //new TodoList(1, 'Karate Kid', [new TodoItem(1, 'Paint the fence')]),
  //   //new TodoList(id, name, [new TodoItem(num2, greet)]),
  // ];

  
  //Set(todoLists);
const todoLists = [];
  function addToList()
  {
    var id = prompt("Enter id#");
    var list_name = prompt("Enter List Name");
    var id2 = prompt("Enter another id");
    var greeting = prompt("Enter a Greeting")
    if(todoLists.includes(list_name))
    {
      
    }
    else
    {
      todoLists.push(new TodoList(parseInt(id), list_name, [new TodoItem(parseInt(id2), greeting)]));
    }
  }
  
  // function addNewList()
  // {
  //   var trigger = true;
  // while(true)
  //   {
  //     var choice = prompt("Would you like to add to the list?");
  //     if(choice.toLowerCase() === 'yes')
  //     {
  //       addToList();
  //     }
  //     else
  //     {
  //       break;
  //     }
  //   }
  // }
  

  const getButton = document.getElementById("button");
  getButton.addEventListener('click', (e) => {
    var clear = document.getElementById("lists-list");
    clear.innerHTML = '';
    looper()
  });

  function renderList() {
    currentList.todos.forEach((t) => 
    {
      const span = document.createElement('span');
      span.innerHTML = t.name;
      document.querySelector('#current-list-target').innerHTML = '';
      document.querySelector('#current-list-target').appendChild(span);
    });
  }
  
  let currentList = null;
  
  
  function looper()
  {
      
      addToList();
      todoLists.forEach((e) => 
    {
      const li = document.createElement('li'); // create a disconnected element
      li.innerHTML = e.name;
      li.setAttribute('data-id', e.id);

      // add a click listener to the li for more interactivity
      // this will set the current list to displayed on the other section of the page
      li.addEventListener('click', (e) => 
      {
        const targetId = parseInt(e.target.getAttribute('data-id'));
        currentList = todoLists.find((l) => l.id === targetId); // high order function
        renderList();
      });
      
      document.querySelector('#lists-list').appendChild(li);// connect the element to the tree as a child of ul#lists-list
  });
  }
  // loop over all the todo lists adding eachone to the aside list for displaying
  

  if (currentList === null) 
  {
    document.querySelector('#current-list-target').innerHTML =
      'No list to display';
  }