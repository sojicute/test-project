// import React from 'react';
// import ReactDOM from 'react-dom';
import App from './App';

const React = require('react'); // <1>
const ReactDOM = require('react-dom'); // <2>

// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <React.StrictMode>
//     <App />
//   </React.StrictMode>
// );

ReactDOM.render(
    <App />,
    document.getElementById('root')
);