import React, {useState} from "react";
import {MagicSquareForm} from "./MagicSquareForm"
import {LexicographicForm} from "./LexicographicForm"


const TYPE = {
    MAGIC_SQUARE: 'MAGIC_SQUARE',
    LEXICOGRAPHIC: 'LEXICOGRAPHIC'
}

function App() {

    const [input, setInput] = useState("");
    const [firstInput, setFirstInput] = useState("");
    const [secondInput, setSecondInput] = useState("");


    const [type, setType] = useState(TYPE.MAGIC_SQUARE);

    const [tasks, setTasks] = useState([]);

    const [result, setResult] = useState({});
    const [error, setError] = useState({});


    const onInputChange = e => setInput(e.target.value);
    const onFirstInputChange = e => setFirstInput(e.target.value);
    const onSecondInputChange = e => setSecondInput(e.target.value);

    const onTypeChange = e => {
        setType(e.target.value)
        setInput("")
        setResult({})
        setFirstInput("")
        setSecondInput("")
    };

    const onFileChange = e => importFile(e.target.files[0]);


    const handleCalcMagicSquare = () => {
        const data = {
            input: input,
            type: type
        }

        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        };

        fetch("http://localhost:8080/api/magic-square", requestOptions)
            .then(response => response.json())
            .then((res) => {
                setResult(res);
                console.log(res);
            })
            .catch(err => {
                console.log(error.response);
                setError(err);
            });
    }

    const handleCalcLexicographic = () => {

        const data = {
            firstInput: firstInput,
            secondInput: secondInput,
            type: type
        }

        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        };

        fetch("http://localhost:8080/api/lexicographic", requestOptions)
            .then(response => response.json())
            .then((res) => setResult(res))
            .catch(err => setError(err));
    }


    const handleSave = () => {

        const data = {
            matrixInput: input,
            firstInput: firstInput,
            secondInput: secondInput,
            type: type
        }
        console.log(data);

        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        };

        fetch("http://localhost:8080/api/task/", requestOptions)
            .then(response => response.json())
            .then((res) => {
                setResult(res);
                console.log(res)
            })
            .catch(err => console.log(err));
    }

    const handleLoadAllTask = () => {
        const requestOptions = {
            method: 'GET',
            headers: {'Content-Type': 'application/json'},
        };

        fetch("http://localhost:8080/api/task/", requestOptions)
            .then(response => response.json())
            .then((res) => {
                setTasks(res);
            })
            .catch(err => console.log(err));

        }


    const handleLoadTask = (e) => {

        const id = e.target.id;

        const requestOptions = {
            method: 'GET',
            headers: {'Content-Type': 'application/json'},
        };

        fetch("http://localhost:8080/api/task/" + id, requestOptions)
            .then(response => response.json())
            .then((res) => {
                console.log(res)
                setType(res.type);
                setInput(res.firstInput)
                setFirstInput(res.firstInput)
                setSecondInput(res.secondInput)
            })
            .catch(err => console.log(err));

    }


    const importFile = (file) => {


        const formData = new FormData();
        formData.append("file", file);
        formData.append("fileName", file.name);
        console.log(formData)
        const requestOptions = {
            method: 'POST',
            body: formData
        };

        fetch("http://localhost:8080/import", requestOptions)
            .then(response => response.json())
            .then((res) => {
                console.log(res)
                setResult(res);
            })
            .catch(err => console.log(err))
    }

    const exportFile = () => {

        const data = {
            matrixInput: input,
            firstInput: firstInput,
            secondInput: secondInput,
            type: type,
        }

        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        };

        fetch("http://localhost:8080/export", requestOptions)
            .then(response => {
                const filename = response.headers.get('Content-Disposition').split('filename=')[1];
                response.blob().then(blob => {
                    let url = window.URL.createObjectURL(blob);
                    let a = document.createElement('a');
                    a.href = url;
                    a.download = filename;
                    a.click();
                });
            })
            .catch(err => console.log(err));

    }


        return (
            <section className="hero is-fullheight ">
                <div className="hero-body">
                    <div className="container">
                        <div className="columns is-8">
                            <div className="columns">
                                <div className="column is-two-thirds">
                                    <div>
                                        {type === TYPE.MAGIC_SQUARE ? (
                                            <div>
                                                <MagicSquareForm input={input}
                                                                 onInputChange={onInputChange}
                                                />
                                                <div className="buttons">
                                                    <button onClick={handleCalcMagicSquare} className="button">Посчитать</button>
                                                </div>
                                            </div>
                                        ) : type === TYPE.LEXICOGRAPHIC ? (
                                            <div>
                                                <LexicographicForm firstInput={firstInput}
                                                                   secondInput={secondInput}
                                                                   onFirstInputChange={onFirstInputChange}
                                                                   onSecondInputChange={onSecondInputChange}

                                                />
                                                <div className="buttons">
                                                    <button onClick={handleCalcLexicographic} className="button">Посчитать</button>
                                                </div>
                                            </div>

                                        ) : null}

                                        <div className="field">
                                            <label className="label">Тип задачи</label>
                                            <div className="select ">
                                                <select
                                                    onChange={onTypeChange}>
                                                    <option value={TYPE.MAGIC_SQUARE}>Magic Square</option>
                                                    <option value={TYPE.LEXICOGRAPHIC}>Lexicographic</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div className="content">
                                            <h6 className="has-text-danger">{result.input ? result.input : ""}</h6>
                                            <h5>Результат: {result.result ? result.result : ""}</h5>
                                        </div>

                                        <div className="buttons">
                                            <button onClick={handleSave} className="button">Сохранить</button>
                                            <button onClick={exportFile} className="button">Экспортировать</button>
                                            <button onClick={handleLoadAllTask} className="button">Загрузить</button>
                                        </div>

                                        <div className="file is-boxed">
                                            <label className="file-label">
                                                <input
                                                    onChange={onFileChange}
                                                    className="file-input"
                                                    type="file"
                                                />
                                                <span className="file-cta">
                                                <span className="file-icon">
                                                    <ion-icon name="cloud-upload-outline"></ion-icon>
                                                </span>
                                                <span className="file-label">
                                                    <p>Импортировать</p>
                                                </span>
                                            </span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="column is-one-third">
                                <div>
                                    {tasks.map((task) => {
                                        return (
                                            <div onClick={handleLoadTask} key={task.id} id={task.id}
                                                 className="box is-clickable is-relative">
                                                {/*<p>Поле ввода: {task.input}</p>*/}
                                                Тип задачи: {task.type}
                                            </div>
                                        )
                                    })}
                                </div>

                            </div>
                        </div>
                    </div>
                </div>

                <div id="modal-js-example" className="modal">
                    <div className="modal-background"></div>
                    <div className="modal-card">
                        <header className="modal-card-head">
                            <p className="modal-card-title">Modal title</p>
                            <button className="delete" aria-label="close"></button>
                        </header>
                        <section className="modal-card-body">
                        </section>
                        <footer className="modal-card-foot">
                            <button className="button is-success">Save changes</button>
                            <button className="button">Cancel</button>
                        </footer>
                    </div>
                </div>

            </section>
        );
}

export default App;
