import React from "react";

export function LexicographicForm({firstInput, secondInput, onFirstInputChange, onSecondInputChange}) {

    return (
        <div className="field">
            <div className="content">
                <h3>Лексикографический порядок</h3>
                <p>Даны два массива строк a1 и a2, которые возвращают отсортированный массив в
                    лексикографическом порядке строк a1, которые являются подстроками строк a2.
                </p>
                <h6>Например:</h6>
                <p>arp, live, strong</p>
                <p>lively, alive, harp, sharp, armstrong</p>
                <h6>Результат:</h6>
                <p>arp, live, strong</p>
            </div>
            <div className="field">
                <label className="label">Первая строка </label>
                <div className="control">
                    <input onChange={onFirstInputChange} className="input" type="text" value={firstInput} placeholder="arp, live, strong"/>
                </div>
            </div>
            <div className="field">
                <label className="label">Вторая строка</label>
                <div className="control">
                    <input onChange={onSecondInputChange} className="input" type="text" value={secondInput} placeholder="lively, alive, harp, sharp, armstrong"/>
                </div>
            </div>

        </div>
    )
}