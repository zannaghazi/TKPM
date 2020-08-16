import React from 'react';
import { Table } from 'react-bootstrap';
import styles from './static/styles.module.css'
import config from '../../../asset/config.json'
import { useState, useEffect } from 'react';

function TableContainer(props) {
    const [data, setData] = useState([])
    useEffect(() => {
        let url = config.severAPi.hostUrl + ":8081/book/get_book_by_isbn/" + props.ISBN;
        fetch(url, {
            method: "get",
            headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
        })
            .then(res => res.json())
            .then(dataReceive => {
                const listItems = dataReceive.map((element, index) =>
                    <tr key={index}>
                        <th >{element.ID}</th>
                        <th >{element.statusName}</th>
                    </tr>
                );
                setData(listItems);
            });
    }, [props.ISBN]);

    return (
        <div className={[styles.myScroll, "mt-5"].join(" ")}>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th width={'40%'}><b>ID</b></th>
                        <th width={'60%'}><b>Trạng thái</b></th>
                    </tr>
                </thead>
                <tbody className={styles.myScroll}>
                    {data}
                </tbody>
            </Table>
        </div>
    );
}

export default TableContainer;