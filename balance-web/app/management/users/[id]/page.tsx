import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Item, ItemContent, ItemTitle } from "@/components/ui/item";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import ManagementTemplate from "@/components/widgets/pages/management-template";
import * as client from "@/lib/service/client/management/user-rest.client"
import { Calendar, User, UserX } from "lucide-react";
import RetirementComponent from "./_client/retirement-component";


export default async function UserDetailsPage(props : PageProps<'/management/users/[id]'>) {

    const { id } = await props.params
    const result = await client.findById(id)

    return (
        <ManagementTemplate page="User Details" links={[
            {name : "User Management", route : "/management/users"}
        ]}>
            <Card>
                <CardHeader>
                    <CardTitle className="flex justify-between items-center">
                        <div className="flex gap-2 items-start">
                            <User size={20} /> 
                            <div className="flex flex-col gap-1">
                                <span>{result.name}</span>
                                <Badge variant={'secondary'}>{result.status}</Badge>
                            </div>
                        </div>
                        <RetirementComponent userId={result.id} />
                    </CardTitle>
                    <CardContent className="grid grid-cols-3 gap-4">
                        <ProfileItem label="Phone" value={result.phone} />
                        <ProfileItem label="Email" value={result.email} />
                        <ProfileItem label="Assign Date" value={result.assignDate} className="col-start-1" />
                        <ProfileItem label="Activated At" value={result.activatedAt} />
                        <ProfileItem label="Retired At" value={result.retiredAt || "Not yet"} />
                        <ProfileItem label="Contract For this Month" value={String(result.contractForThisMonth)} />
                        <ProfileItem label="Contract Total" value={String(result.contractTotal)} />
                    </CardContent>
                </CardHeader>
            </Card>

            <Card>
                <CardHeader>
                    <CardTitle className="flex items-center gap-2">
                        <Calendar size={20} /> Access History
                    </CardTitle>
                    <CardContent>
                        <Table>
                            <TableHeader>
                                <TableRow>
                                    <TableHead>Access Date</TableHead>
                                    <TableHead>Sign In</TableHead>
                                    <TableHead>Sign Out</TableHead>
                                </TableRow>
                            </TableHeader>
                            <TableBody>
                            {result.accessHistory.map(item => 
                                <TableRow key={item.accessDate}>
                                    <TableCell>{item.accessDate}</TableCell>
                                    <TableCell>{item.signInAt}</TableCell>
                                    <TableCell>{item.signOutAt}</TableCell>
                                </TableRow>
                            )}
                            </TableBody>
                        </Table>
                    </CardContent>
                </CardHeader>
            </Card>
        </ManagementTemplate>
    )
}

function ProfileItem({label, value, className, outline} : {label : string, value : string, className? : string, outline? : boolean}) {
    return (
        <Item className={className} variant={outline ? 'outline' : 'default'}>
            <ItemContent>
                <ItemTitle>{label}</ItemTitle>
                <ItemContent>{value}</ItemContent>
            </ItemContent>
        </Item>
    )
}