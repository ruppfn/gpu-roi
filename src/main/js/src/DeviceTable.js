import React, {useCallback, useEffect, useState} from 'react';
import {DataGrid} from "@mui/x-data-grid";
import Snackbar from '@mui/material/Snackbar';
import Alert from '@mui/material/Alert';
import axios from "axios";

function DeviceTable() {

    const URL = "/api/devices";
    // const URL = "http://localhost:8080/api/devices";

    const [snackbar, setSnackbar] = useState(null);
    const handleCloseSnackbar = () => setSnackbar(null);

    const [devices, setDevices] = useState([]);
    const [pageNumber, setPageNumber] = useState(0);
    const [totalElements, setTotalElements] = useState(0);
    const [pageSize, setPageSize] = useState(0);
    const sortModel = [
        {
            field: "paying",
            sort: "desc"
        }
    ];

    const handleRowChange = useCallback(
        async (params) => {

            const updatePrice = async (deviceId, price) => {
                const request = {
                    deviceId, price
                };
                const response = await axios.put(URL, request).then(response => response.data);
                console.log("Update Price response: ", response);
                return response;
            };

            try {
                const response = await updatePrice(params.id, params.value);

                setSnackbar({ children: 'Price successfully saved', severity: 'success' });
                setDevices((prev) =>
                    prev.map((row) => (row.id === params.id ? { ...row, ...response } : row)),
                );
            } catch (error) {
                setSnackbar({ children: 'Error while saving price', severity: 'error' });
                // Restore the row in case of error
                setDevices((prev) => [...prev]);
            }
        },
        [setDevices],
    );

    useEffect(() => {
        axios.get(URL, {
            params: {pageNumber}
        }).then(response => {
            const data = response.data;
            console.log("Response: {}", data);
            setDevices(data.content);
            setPageNumber(data.pageNumber);
            setTotalElements(data.totalElements);
            setPageSize(data.pageSize);
        });
    }, [pageNumber, setDevices, setPageNumber, setTotalElements, setPageSize]);

    const columns = [
        {field: "name", headerName: "Name", flex: 4},
        {field: "power", headerName: "Power", flex: 1, renderCell: cell => powerFormat(cell) },
        {field: "paying", headerName: "BTC per Day", flex: 2},
        {field: "priceInArs", headerName: "Price in ARS", flex: 1, editable: true},
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
                    onCellEditCommit={handleRowChange}
                />
            </div>
            {!!snackbar && (
                <Snackbar open onClose={handleCloseSnackbar} autoHideDuration={6000}>
                    <Alert {...snackbar} onClose={handleCloseSnackbar} />
                </Snackbar>
            )}
        </div>
    );
}

export default DeviceTable;
