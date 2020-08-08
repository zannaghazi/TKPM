import React from 'react';
import {Table} from 'react-bootstrap';

function TableContainer(props) {
    return (
        <Table striped bordered hover>
            <thead>
                <tr>
                    <th width={'2%'}>#</th>
                    <th width={'45%'}>Tên sách</th>
                    <th width={'28%'}>ISBN</th>
                    <th width={'17%'}>Trạng thái</th>
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