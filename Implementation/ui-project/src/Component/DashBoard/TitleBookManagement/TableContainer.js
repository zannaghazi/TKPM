import React from 'react';
import { Table, Button } from 'react-bootstrap';
import styles from '../static/styles.module.css'

function TableContainer(props) {
    return (
        <Table striped bordered hover>
            <thead>
                <tr>
                    <th width={'2%'}>ISBN</th>
                    <th width={'15%'}>Tên</th>
                    <th width={'15%'}>Tác giả</th>
                    <th >Vị trí</th>
                    <th width={'10%'}>Ngày phát hành</th>
                    <th width={'15%'}>Thể loại</th>
                    <th width={'8%'}>
                    </th>
                </tr>
            </thead>
            <tbody>
                
            </tbody>
        </Table>
    );
}

export default TableContainer;