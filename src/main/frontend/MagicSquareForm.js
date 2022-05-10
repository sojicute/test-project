import React from "react";

export function MagicSquareForm({input, onInputChange}) {

    return (
        <div className="field">
            <div className="content">
                <h3>Магический квадрат</h3>
                <p>Дана таблица 3х3, необходимо превратить её в (полу)магический квадрат за наименьшую стоимость.
                </p>
                <h6>Например:</h6>

                <p>1 2 3 4 5 6 7 8 9
                    Превращается в

                    4 3 8 2 7 6 9 5 1

                    за стоимость равную 26.
                </p>
            </div>

            <div className="field">
                <label className="label">Введите цифры от уникальные цифры 1 до 9</label>
                <div className="control">
                    <input onChange={onInputChange} className="input" type="text" value={input} placeholder="123456789"/>
                </div>
            </div>
        </div>
    )
}