import React from 'react';
import { Table, Button } from 'react-bootstrap';
import styles from '../static/styles.module.css'

function TableContainer(props) {
    return (
        <Table striped bordered hover>
            <thead>
                <tr>
                    <th width={'2%'}>ID</th>
                    <th width={'20%'}>ISBN</th>
                    <th >Tên sách</th>
                    <th width={'20%'}>Trạng thái</th>
                </tr>
            </thead>
            <tbody>
                
            </tbody>
        </Table>
    );
}

export default TableContainer;