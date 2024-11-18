let x = 'X'; // User plays with X
let o = 'O'; // Computer plays with O
let postionX;
let postionY;
let randomXnum;
let randomYnum;
let index = 0;

const arrX = ['X', 'X', 'X'];
const arrY = ['O', 'O', 'O'];
const fdkBox = document.getElementById('feedback-box');
const content = document.getElementById('content');

let ticToc = [
  ['U', 'U', 'U'],
  ['U', 'U', 'U'],
  ['U', 'U', 'U'],
];
localStorage.setItem('ticToc', JSON.stringify(ticToc));
const btn = [
  ['btn1', 'btn2', 'btn3'],
  ['btn4', 'btn5', 'btn6'],
  ['btn7', 'btn8', 'btn9'],
];

const actions = {
  btn1: () => buttonClick(ticToc, 0, 0),
  btn2: () => buttonClick(ticToc, 0, 1),
  btn3: () => buttonClick(ticToc, 0, 2),
  btn4: () => buttonClick(ticToc, 1, 0),
  btn5: () => buttonClick(ticToc, 1, 1),
  btn6: () => buttonClick(ticToc, 1, 2),
  btn7: () => buttonClick(ticToc, 2, 0),
  btn8: () => buttonClick(ticToc, 2, 1),
  btn9: () => buttonClick(ticToc, 2, 2),
};

// Add event listener to all buttons

function buttonClick(ticToc, X, Y) {
  postionX = X;
  postionY = Y;
  localStorage.setItem('ticToc', JSON.stringify(ticToc));
  TicTocGame();
  // ticToc[X][Y] = x;
}

function TicTocGame() {
  ticToc = getTicTocFromLocalStorage();
  playGame(ticToc); // Player's move
  if (Check(x) === 'X') {
    feedback('The User Wins');
    localStorage.removeItem('ticToc');
    return;
  }
  if (index >= 7) {
    feedback("It's a draw");
    localStorage.removeItem('ticToc');
    return;
  }
  randomXnum = Math.floor(Math.random() * 3);
  randomYnum = Math.floor(Math.random() * 3);
  playGameComp(ticToc); // Computer's move
  if (Check(o) === 'O') {
    feedback('The Computer Wins');
    localStorage.removeItem('ticToc');
    return;
  }
  localStorage.setItem('ticToc', JSON.stringify(ticToc));
}

function getTicTocFromLocalStorage() {
  const storedTicToc = localStorage.getItem('ticToc');
  if (storedTicToc) {
    return JSON.parse(storedTicToc);
  }
}

function feedback(str) {
  const par = document.createElement('p');
  par.className = 'text';
  par.textContent = str;
  content.appendChild(par);
  fdkBox.style.display = 'block';
  const background = document.querySelector('.container');
  const overlay = document.getElementById('overlay');
  const body = document.getElementById('body1');

  background.style.filter = 'blur(5px)';
  // const overlay = document.querySelector('.overlay');
  // overlay.style.display = 'block';
  overlay.style.backgroundColor = 'lightgrey';
  body.style.backgroundColor = 'lightgrey';
}

function createIcon(classes) {
  const icon = document.createElement('i');
  icon.className = classes;
  return icon;
}

function checkPositionUser() {
  if (checkIfPositionOccupied(ticToc, postionX, postionY)) {
    feedback('Position is Occupied (Select another one)');
    setTimeout(() => {
      fdkBox.style.display = 'none';
    }, 3000);
  }
}

function playGame(ticToc) {
  checkPositionUser();
  ticToc[postionX][postionY] = x;
  const btnUser = document.getElementById(btn[postionX][postionY]);
  if (!btnUser.querySelector('i')) {
    // If no <i> element is found in the button
    const icon = createIcon('fa-solid fa-xmark');

    btnUser.appendChild(icon); // Append the icon
  }
}

function checkPositionComp() {
  if (checkIfPositionOccupied(ticToc, randomXnum, randomYnum)) {
    randomXnum = Math.floor(Math.random() * 3);
    randomYnum = Math.floor(Math.random() * 3);
    playGameComp(ticToc);
  }
}

function playGameComp(ticToc) {
  checkPositionComp();
  ticToc[randomXnum][randomYnum] = o;
  const btnComp = document.getElementById(btn[randomXnum][randomYnum]);

  if (!btnComp.querySelector('i')) {
    // If no <i> element is found in the button
    const icon = createIcon('fa-regular fa-circle');
    btnComp.appendChild(icon);
    // Append the icon
  }
  index++;
}

function Check(str) {
  const tempArr = str === x ? arrX : arrY;
  let tempX = ['U', 'U', 'U'];

  // Check rows}

  for (let i = 0; i < ticToc.length; i++) {
    for (let j = 0; j < ticToc.length; j++)
      if (ticToc[i][j] === str) {
        tempX[j] = str;
      }
    if (ArraysEquals(tempX, tempArr)) {
      return str;
    } else {
      tempX = setArrayToNothing(tempX);
    }
  }
  //Checking the Verticals
  for (let i = 0; i < ticToc.length; i++) {
    for (let j = 0; j < ticToc.length; j++)
      if (ticToc[j][i] === str) {
        tempX[j] = str;
      }
    if (ArraysEquals(tempX, tempArr)) {
      return str;
    } else {
      tempX = setArrayToNothing(tempX);
    }
  }
  //Check the diagonal having the same numbers
  let i1 = 0;
  for (let j = 0; j < ticToc.length; j++) {
    if (ticToc[j][i1] === str) {
      tempX[i1] = str;
      i1++;
    }
  }

  if (ArraysEquals(tempX, tempArr)) {
    return str;
  } else {
    tempX = setArrayToNothing(tempX);
  }

  //Check the diagonal the opposite numbers
  let i2 = ticToc.length - 1;
  for (let j = 0; j < ticToc.length; j++) {
    if (ticToc[j][i2] === str) {
      tempX[i2] = str;
      i2--;
    }
  }

  if (ArraysEquals(tempX, tempArr)) {
    return str;
  } else {
    tempX = setArrayToNothing(tempX);
  }

  //}
  return 'b';
}

function checkIfPositionOccupied(ticToc, X, Y) {
  return ticToc[X][Y] !== 'U';
}

function ArraysEquals(arr1, arr2) {
  return JSON.stringify(arr1) === JSON.stringify(arr2);
}
function setArrayToNothing(array) {
  if (array.length > 0) {
    for (let i = 0; i < array.length; i++) {
      array[i] = 'U';
    }
    return array;
  }
  return array;
}

document.querySelectorAll('button').forEach((button) => {
  button.addEventListener('click', (event) => {
    const action = actions[event.target.id]; // Get the corresponding action
    if (action) {
      action(); // Call the action
    }
  });
});
