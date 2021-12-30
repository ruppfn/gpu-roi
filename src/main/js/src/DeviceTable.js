import React, {useEffect, useState} from 'react';
import {DataGrid} from "@mui/x-data-grid";
import axios from "axios";

function DeviceTable() {

    const URL = "/api/devices";
    // const URL = "http://localhost:8080/api/devices";

    const [devices, setDevices] = useState([]);
    const [pageNumber, setPageNumber] = useState(0);
    const [totalElements, setTotalElements] = useState(0);
    const [sortModel, setSortModel] = useState([
        {
            field: "paying",
            sort: "desc"
        }
    ]);

    const pageSize = 10;

    useEffect(() => {
        axios.get(URL, {
            params: {pageNumber}
        }).then(response => {
            const data = response.data;

            setDevices(data.content);
            setPageNumber(data.pageNumber);
            setTotalElements(data.totalElements);
        });
        console.log(devices);
    }, [pageNumber, pageSize]);

    const columns = [
        {field: "name", headerName: "Name"},
        {field: "power", headerName: "Power"},
        {field: "paying", headerName: "BTC per Day"},
        {field: "priceInArs", headerName: "Price in ARS"},
        {field: "daysToROI", headerName: "Days to ROI"}
    ];

    return (
        <div style={{ height: '100vh', width: '100%' }}>
            <DataGrid
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
    );
}

export default DeviceTable;
