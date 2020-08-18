import React from 'react';
import { Table, Button } from 'react-bootstrap';
import styles from '../static/styles.module.css'

function TableContainer(props) {
    const listItems = props.listBook.map((element, index) =>
        <tr key={index}>
            <td>{element.ID}</td>
            <td>{element.ISBN}</td>
            <td>{element.statusName}</td>
        </tr>);

    return (
        <Table striped bordered hover>
            <thead>
                <tr>
                    <th width={'10%'}>ID</th>
                    <th width={'50%'}>ISBN</th>
                    <th width={'40%'}>Trạng thái</th>
                </tr>
            </thead>
            <tbody>
                {listItems}
            </tbody>
        </Table>
    );
}

export default TableContainer;