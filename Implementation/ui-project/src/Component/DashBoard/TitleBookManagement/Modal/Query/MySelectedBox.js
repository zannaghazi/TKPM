import React from 'react';
import styles from '../static/styles.module.css'

function MySelectedBox(props) {
    return (
        <div className={styles.box}>
            <select className={styles.mySelect}>
                <option value="0">Tên</option>
                <option value="1">Mã ISBN</option>
                <option value="2">Mã sách</option>
                <option value="3">Tên tác giả</option>
                <option value="4">Nhà xuất bản</option>
                <option value="5">Năm xuất bản</option>
                <option value="6">Thể loại</option>
            </select>
        </div>
    );
}

export default MySelectedBox;