import React from 'react';
import {Table} from 'react-bootstrap';

function TableContainer(props) {
    return (
        <Table striped bordered hover>
            <thead>
                <tr>
                    <th width={'2%'}>#</th>
                    <th width={'40%'}>Tên</th>
                    <th width={'22%'}>Ngày cập nhật</th>
                    <th width={'28%'}>Người cập nhật</th>
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