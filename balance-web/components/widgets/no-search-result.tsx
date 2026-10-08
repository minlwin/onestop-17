import { Info } from "lucide-react";
import { Card, CardContent, CardHeader, CardTitle } from "../ui/card";

export default function NoSearchResult({ data } : { data : string }) {
    return (
        <Card>
            <CardHeader>
                <CardTitle className="flex items-center gap-2">
                    <Info size={20} /> No Search Result
                </CardTitle>
            </CardHeader>
            <CardContent>
                There is no {data}. Please change search condition and search again.
            </CardContent>
        </Card>
    )
}