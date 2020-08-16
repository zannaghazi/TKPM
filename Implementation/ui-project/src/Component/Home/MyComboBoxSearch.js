import React from 'react';
import styles from './static/styles.module.css'

function MyComboBoxSearch(props) {
    function handleChange(event){
        props.setSelectValue(event.target.value);
    }

    return (
        <div className={styles.box}>
            <select className = {styles.mySelect} onChange={handleChange}>
                <option value="0">Tên sách</option>
                <option value="1">Mã ISBN</option>
                <option value="2">Tên tác giả</option>
                <option value="3">Nhà xuất bản</option>
            </select>
        </div>
        )
}

export default MyComboBoxSearch;