import { Button } from "@/components/ui/button";
import { Card, CardContent } from "@/components/ui/card";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import DetailsLink from "@/components/widgets/details-link";
import NoMasterData from "@/components/widgets/no-master-data";
import ManagementTemplate from "@/components/widgets/pages/management-template";

import * as client from "@/lib/service/client/management/subscription-plan.client"
import { ChevronRight, Plus } from "lucide-react";
import Link from "next/link";


export default function PlanMasterPage() {
    return (
        <ManagementTemplate page="Subscription Plan Master" >
            <nav className="flex justify-end">
                <Button nativeButton={false} render={
                    <Link href={'/management/master/plans/create'} />
                }>
                    <Plus /> Create Plan
                </Button>
            </nav>
            <PlanMasterList />
        </ManagementTemplate>
    )
}

async function PlanMasterList() {
    const plans = await client.findAll()

    if(plans.length === 0) {
        return (
            <NoMasterData data="Subscription Plan" />
        )
    }
    
    return (
        <Card>
            <CardContent>
                <Table>
                    <TableHeader>
                        <TableRow>
                            <TableHead>Plan Name</TableHead>
                            <TableHead>Price</TableHead>
                            <TableHead>Employee Limit</TableHead>
                            <TableHead>Ledger Limit</TableHead>
                            <TableHead>Daily Entry Limit</TableHead>
                            <TableHead>Created At</TableHead>
                            <TableHead>Modified At</TableHead>
                            <TableHead></TableHead>
                        </TableRow>
                    </TableHeader>

                    <TableBody>
                    {plans.map(item => 
                        <TableRow key={item.id}>
                            <TableCell>{item.name}</TableCell>
                            <TableCell>{item.price}</TableCell>
                            <TableCell>{item.maxEmployee}</TableCell>
                            <TableCell>{item.maxLedger}</TableCell>
                            <TableCell>{item.maxEntry}</TableCell>
                            <TableCell>{item.createdAt}</TableCell>
                            <TableCell>{item.modifiedAt}</TableCell>
                            <TableCell>
                                <DetailsLink route={`/management/master/plans/${item.id}`} />
                            </TableCell>
                        </TableRow>
                    )}    
                    </TableBody>
                </Table>
            </CardContent>
        </Card>
    )
}

