import { Card, CardContent, CardHeader, CardTitle } from "../ui/card";

export default function NoMasterData({data} : {data : string}) {
    return (
        <Card>
            <CardHeader>
                <CardTitle>No Master Data</CardTitle>
            </CardHeader>

            <CardContent>
                {`There is no ${data}. Please create new data to launch application.`}
            </CardContent>
        </Card>
    )
}