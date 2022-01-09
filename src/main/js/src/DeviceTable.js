import React, {useEffect, useState} from 'react';
import {DataGrid} from "@mui/x-data-grid";
import axios from "axios";

function DeviceTable() {

    const URL = "/api/devices";
    // const URL = "http://localhost:8080/api/devices";

    const [devices, setDevices] = useState([]);
    const [pageNumber, setPageNumber] = useState(0);
    const [totalElements, setTotalElements] = useState(0);
    const sortModel = [
        {
            field: "paying",
            sort: "desc"
        }
    ];

    const pageSize = 10;

    useEffect(() => {
        axios.get(URL, {
            params: {pageNumber}
        }).then(response => {
            const data = response.data;
            console.log("Response: {}", data);
            setDevices(data.content);
            setPageNumber(data.pageNumber);
            setTotalElements(data.totalElements);
        });
    }, [pageNumber, setDevices, setPageNumber, setTotalElements]);

    const columns = [
        {field: "name", headerName: "Name", flex: 4},
        {field: "power", headerName: "Power", flex: 1, renderCell: cell => powerFormat(cell) },
        {field: "paying", headerName: "BTC per Day", flex: 2},
        {field: "priceInArs", headerName: "Price in ARS", flex: 1},
        {field: "daysToROI", headerName: "Days to ROI", flex: 1}
    ];

    const powerFormat = row => <div>{row.value}&nbsp;<b>W</b></div>;

    return (
        <div style={{display: 'flex', height: '100%'}}>
            <div style={{ flexGrow: 1 }}>
                <DataGrid
                    autoHeight
                    pagination
                    paginationMode="server"
                    rows={devices}
                    rowCount={totalElements}
                    columns={columns}
                    pageSize={pageSize}
                    page={pageNumber}
                    onPageChange={newPageNumber => setPageNumber(newPageNumber)}
                    sortModel={sortModel}
                />
            </div>
        </div>
    );
}

export default DeviceTable;
