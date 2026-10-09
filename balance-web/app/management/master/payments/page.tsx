import { Button } from "@/components/ui/button";
import { Card, CardContent } from "@/components/ui/card";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import DetailsLink from "@/components/widgets/details-link";
import NoMasterData from "@/components/widgets/no-master-data";
import ManagementTemplate from "@/components/widgets/pages/management-template";

import * as client from "@/lib/service/client/management/payment-info.client"
import { PaymentInfo } from "@/lib/types";
import { Plus } from "lucide-react";
import Link from "next/link";

export default async function PagementMasterPage() {

    const list = await client.findAll()

    return (
        <ManagementTemplate page="Payment Master" >
            <nav className="flex justify-end">
                <Button nativeButton={false} render={
                    <Link href={'/management/master/payments/create'} />
                }>
                    <Plus /> Create Payment Info
                </Button>
            </nav>

            <PaymentInfoList list={list} />
        </ManagementTemplate>
    )
}

function PaymentInfoList({ list } : { list : PaymentInfo []}) {
    if(list.length === 0) {
        return (
            <NoMasterData data="Payment Info" />
        )
    }

    return (
        <Card>
            <CardContent>
                <Table>
                    <TableHeader>
                        <TableRow>
                            <TableHead>Payment Provider</TableHead>
                            <TableHead>Account No.</TableHead>
                            <TableHead>Account Name</TableHead>
                            <TableHead>Created At</TableHead>
                            <TableHead>Modified At</TableHead>
                            <TableHead></TableHead>
                        </TableRow>
                    </TableHeader>

                    <TableBody>
                        {list.map(item => 
                            <TableRow key={item.id}>
                                <TableCell>{item.provider}</TableCell>
                                <TableCell>{item.accountNo}</TableCell>
                                <TableCell>{item.accountName}</TableCell>
                                <TableCell>{item.createdAt}</TableCell>
                                <TableCell>{item.modifiedAt}</TableCell>
                                <TableCell>
                                    <DetailsLink route={`/management/master/payments/${item.id}`} />
                                </TableCell>
                            </TableRow>
                        )}
                    </TableBody>
                </Table>
            </CardContent>
        </Card>
    )
}