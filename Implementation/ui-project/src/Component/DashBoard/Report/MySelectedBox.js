import React from 'react';
import styles from '../static/styles.module.css'

function MySelectedBox(props) {
    function handleChange(event){
        props.byValue(event.target.value);
    }

    return (
        <div className={styles.box}>
            <select className={[styles.mySelect, "ml-2 mr-2"].join(" ")} onChange={handleChange}>
                <option value="0">Sách mượn</option>
                <option value="1">Sách trả</option>
                <option value="2">Sách nhập</option>
                <option value="3">Sách mất</option>
                <option value="4">Sách thanh lý</option>
            </select>
        </div>
    );
}

export default MySelectedBox;