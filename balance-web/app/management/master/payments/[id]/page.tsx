import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import AuditSection from "@/components/widgets/audit-section";
import Info from "@/components/widgets/info";
import ManagementTemplate from "@/components/widgets/pages/management-template";
import SectionTitle from "@/components/widgets/section-title";
import * as client from "@/lib/service/client/management/payment-info.client"
import { Banknote, Pencil } from "lucide-react";
import Link from "next/link";

export default async function PaymentInfoDetailsPage(props : PageProps<'/management/master/payments/[id]'>) {

    const { id } = await props.params
    const details = await client.findById(id)

    return (
        <ManagementTemplate page="Deatils">
            <Card>
                <CardHeader>
                    <CardTitle className="flex justify-between">
                        <div className="flex gap-2">
                            <Banknote /> 
                            Payment Information
                        </div>

                        <Button nativeButton={false} render={
                            <Link href={`/management/master/payments/${details.id}/edit`} />
                        }>
                            <Pencil /> Edit Payment Info
                        </Button>
                    </CardTitle>
                </CardHeader>

                <CardContent className="space-y-6">
                    <section className="space-y-4">
                        <SectionTitle title="Account" />
                        <div className="grid grid-cols-4 gap-4">
                            <Info label="Payment Provider" value={details.provider} />
                            <Info label="Account No" value={details.accountNo} />
                            <Info label="Account Name" value={details.accountName} />
                        </div>
                    </section>

                    <AuditSection createdBy={details.createdBy} createdAt={details.createdAt}
                        modifiedBy={details.modifiedBy} modifiedAt={details.modifiedAt} />

                </CardContent>
            </Card>
        </ManagementTemplate>
    )
}